package com.megacrit.cardcrawl.cards.colorless;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.unique.*;

public class Improvise
  extends AbstractCard
{
  public static final String ID = "Improvise";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Improvise");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int POOL = 1;
  private static final int START_AMT = 1;
  private static final int UPG_AMT = 1;
  
  public Improvise()
  {
    super("Improvise", NAME, "status/beta", "status/beta", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
    
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ImprovAction(this.upgraded));
  }
  
  public AbstractCard makeCopy()
  {
    return new Improvise();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
